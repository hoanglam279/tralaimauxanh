package com.doan.project.web.project.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Event {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String address;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime timeStart;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime timeEnd;
	
	@Column(columnDefinition = "text", length = 65535)
	private String image;
	
	// toi da nguoi co the join duoc
	private Integer umberUserJoin;
	
	private String status;
	
	// 0 : chưa dc approval
	// 1 : chưa bắt đầu
	// 2 : đang diễn ra
	// 3 : đã close
	private String statusProcess;
	
	// 0 : chưa vượt quá
	// 1 : đã đủ số người
	private String statusMax;
	
	// ignore
	// false : chua dc nguoi tạo approval
	// true : đã được người tạo approval
	private boolean statusApprovalJoin;
	
	// so luong nguoi da joined
	private Integer numberUserJoined;
	
	private Integer idUser;
	
	public Event() {
		super();
	}

	public Event(Integer id, String name, String description, String address, LocalDateTime timeStart,
			LocalDateTime timeEnd, String image, Integer umberUserJoin, String status, String statusProcess,
			String statusMax, Integer numberUserJoined, Integer idUser) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.image = image;
		this.umberUserJoin = umberUserJoin;
		this.status = status;
		this.statusProcess = statusProcess;
		this.statusMax = statusMax;
		this.numberUserJoined = numberUserJoined;
		this.idUser = idUser;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getUmberUserJoin() {
		return umberUserJoin;
	}

	public void setUmberUserJoin(Integer umberUserJoin) {
		this.umberUserJoin = umberUserJoin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getStatusProcess() {
		return statusProcess;
	}

	public void setStatusProcess(String statusProcess) {
		this.statusProcess = statusProcess;
	}

	public String getStatusMax() {
		return statusMax;
	}

	public void setStatusMax(String statusMax) {
		this.statusMax = statusMax;
	}

	public Integer getNumberUserJoined() {
		return numberUserJoined;
	}

	public void setNumberUserJoined(Integer numberUserJoined) {
		this.numberUserJoined = numberUserJoined;
	}

	public boolean isStatusApprovalJoin() {
		return statusApprovalJoin;
	}

	public void setStatusApprovalJoin(boolean statusApprovalJoin) {
		this.statusApprovalJoin = statusApprovalJoin;
	}
	
}
