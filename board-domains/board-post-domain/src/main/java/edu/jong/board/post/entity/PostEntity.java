package edu.jong.board.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.jong.board.common.BoardConstants.TableNames;
import edu.jong.board.domain.entity.BaseEntity;
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

	@Column(name = "post_writer_no",
			nullable = false)
	private long writerNo;

	@Builder
	public PostEntity(String title, String content, long views, long writerNo) {
		super();
		this.title = title;
		this.content = content;
		this.views = views;
		this.writerNo = writerNo;
	}
	
	public void increaseViews() {
		this.views += 1;
	}

}
