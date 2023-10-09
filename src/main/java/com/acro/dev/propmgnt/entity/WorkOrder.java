package com.acro.dev.propmgnt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="workorder")
public class WorkOrder extends  BaseEntity{
    @Column(name="problem_description")
    private String ProblemDescription;
    @Column(name="typeof_workorder")
    private String typeOfWorkOrder;
    @Column(name="wo_date")
    private Date woDate;
    @Column(name="wo_startdate")
    private Date woStartDate;
    @Column(name="wo_enddate")
    private Date woEndDate;
    @Column(name="permission_toenter")
    private String permissionToEnter;




    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="property_id",referencedColumnName = "id")
    private Property property;

   /* @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="tenant_id",referencedColumnName = "id")
    private Tenant tenant;*/

}

