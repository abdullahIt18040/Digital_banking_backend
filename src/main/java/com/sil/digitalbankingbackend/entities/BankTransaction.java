package com.sil.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bank_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "si_no")
    private Integer siNo;

    @Column(name = "brn_code")
    private String brnCode;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "trans_date")
    private LocalDate transDate;

    @Column(name = "purpose_code")
    private String purposeCode;

    @Column(name = "batch_si")
    private String batchSi;

    @Column(name = "leg_si")
    private String legSi;

    @Column(name = "accounting_branch_code")
    private String accountingBranchCode;

    @Column(name = "accounting_branch_name")
    private String accountingBranchName;

    @Column(name = "dr_cr")
    private String drCr;

    @Column(name = "gl_acc_code")
    private String glAccCode;

    @Column(name = "gl_name")
    private String glName;

    @Column(name = "acnt_num")
    private String acntNum;

    @Column(name = "acnt_name")
    private String acntName;


}
