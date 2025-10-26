package com.sil.digitalbankingbackend.services;

import com.sil.digitalbankingbackend.entities.BankTransaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BankTransactionService {

    @PersistenceContext
    private EntityManager entityManager;

    private final int BATCH_SIZE = 100; // flush & clear batch size

    // Async method for inserting a chunk
    @Async

    public CompletableFuture<Void> saveChunkAsync(List<BankTransaction> chunk) {
        for (int i = 0; i < chunk.size(); i++) {
            entityManager.persist(chunk.get(i));

            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
        return CompletableFuture.completedFuture(null);
    }
    @Transactional
    // Split full list into chunks and run async
    public void saveAllMultiThreaded(List<BankTransaction> fullList, int chunkSize) {
        List<List<BankTransaction>> chunks = splitList(fullList, chunkSize);
        CompletableFuture<?>[] futures = chunks.stream()
                .map(this::saveChunkAsync)
                .toArray(CompletableFuture[]::new);

        // wait for all threads to complete
        CompletableFuture.allOf(futures).join();
    }

    // Utility to split list into chunks
    private List<List<BankTransaction>> splitList(List<BankTransaction> list, int chunkSize) {
        int size = list.size();
        int start = 0;
        List<List<BankTransaction>> chunks = new java.util.ArrayList<>();
        while (start < size) {
            int end = Math.min(start + chunkSize, size);
            chunks.add(list.subList(start, end));
            start = end;
        }
        return chunks;
    }

}
