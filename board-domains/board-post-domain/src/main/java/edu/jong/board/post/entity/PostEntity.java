package edu.jong.board.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import edu.jong.board.common.BoardConstants.TableNames;
import edu.jong.board.domain.entity.BaseEntity;
import edu.jong.board.member.entity.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = TableNames.TB_POST)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_no",
			unique = true,
			nullable = false)
	private long no;
	
	@Setter
	@Column(name = "post_title",
			length = 300,
			nullable = false)
	private String title;
	
	@Setter
	@Column(name = "post_content",
			columnDefinition = "TEXT",
			nullable = false)
	private String content;
	
	@Column(name = "post_views",
			nullable = false)
	private long views = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_writer_no",
				nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private MemberEntity writer;

	public void increaseViews() {
		this.views += 1;
	}
	
	@Builder
	public PostEntity(String title, String content, long views, MemberEntity writer) {
		super();
		this.title = title;
		this.content = content;
		this.views = views;
		this.writer = writer;
	}
	
}
