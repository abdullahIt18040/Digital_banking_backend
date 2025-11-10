package com.sil.digitalbankingbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
//abdullah
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "si_no")
    @JsonProperty("si_no")
    private Integer siNo;

    @JsonProperty("brn_code")
    @Column(name = "brn_code")
    private String brnCode;

    @JsonProperty("branch_name")
    @Column(name = "branch_name")
    private String branchName;

    @JsonProperty("trans_date")
    @Column(name = "trans_date")
    private LocalDate transDate;

    @JsonProperty("purpose_code")
    @Column(name = "purpose_code")
    private String purposeCode;

    @JsonProperty("batch_si")
    @Column(name = "batch_si")
    private String batchSi;

    @JsonProperty("leg_si")
    @Column(name = "leg_si")
    private String legSi;

    @JsonProperty("accounting_branch_code")
    @Column(name = "accounting_branch_code")
    private String accountingBranchCode;

    @JsonProperty("accounting_branch_name")
    @Column(name = "accounting_branch_name")
    private String accountingBranchName;


    @JsonProperty("dr_cr")
    @Column(name = "dr_cr")
    private String drCr;

    @JsonProperty("gl_acc_code")
    @Column(name = "gl_acc_code")
    private String glAccCode;

    @JsonProperty("gl_name")
    @Column(name = "gl_name")
    private String glName;

    @JsonProperty("acnt_num")
    @Column(name = "acnt_num")
    private String acntNum;

    @JsonProperty("acnt_name")
    @Column(name = "acnt_name")
    private String acntName;


}
