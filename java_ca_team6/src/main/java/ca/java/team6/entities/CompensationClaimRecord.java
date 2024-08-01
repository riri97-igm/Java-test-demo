package ca.java.team6.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "CompensationClaimRecords")
public class CompensationClaimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClaimId", nullable = false)
    private long claimId;
    
   
	@ManyToOne
    @JoinColumn(name="EmpId", referencedColumnName = "EmpId")
    private Employee employee;

	@Column(name = "ClaimQty", nullable = false)
    private double claimQty;

    @Column(name = "Status", nullable = false, length = 50)
    private String status;

    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "CreateTime")
    private LocalDateTime createTime;

    @Column(name = "LastUpdateTime")
    private LocalDateTime lastUpdateTime;

    @PrePersist
    protected void onCreate() {
        setCreateTime(LocalDateTime.now());
        setLastUpdateTime(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        setLastUpdateTime(LocalDateTime.now());
    }
    
    public CompensationClaimRecord() {
    }

    public CompensationClaimRecord(Employee employee, double claimQty, String status, String remarks) {

        this.employee = employee;
        this.claimQty = claimQty;
        this.status = status;
        this.remarks = remarks;
        this.setCreateTime(LocalDateTime.now());  
        this.setLastUpdateTime(LocalDateTime.now());  
    }
    
    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getClaimQty() {
        return claimQty;
    }

    public void setClaimQty(double claimQty) {
        this.claimQty = claimQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Override
	public String toString() {
		return "CompensationClaimRecord [claimId=" + claimId + ", employee=" + employee + ", claimQty=" + claimQty
				+ ", status=" + status + ", remarks=" + remarks + ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}

}
