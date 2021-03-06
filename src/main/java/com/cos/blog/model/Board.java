package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이
	private String content;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // 셀렉할때 무조껀 들고
	@JoinColumn(name="userId")
	private User user;
	
	// 연관 관계에 있어, 주인이 아니다.
	// fk 가 아니다.
	// db 컬럼을 만들지 마세요.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	

}
