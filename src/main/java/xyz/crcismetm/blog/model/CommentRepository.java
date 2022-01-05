package xyz.crcismetm.blog.model;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.database.Repository;

import java.util.List;

public class CommentRepository extends Repository<Comment> {

    public CommentRepository(Database database) {
        super("comment", Comment.class, database);
        create(
                "id INT PRIMARY KEY AUTO_INCREMENT",
                "articleID INT NOT NULL",
                "userID CHAR(16) NOT NULL",
                "content VARCHAR(126) NOT NULL",
                "edited TINYINT DEFAULT 0",
                "lastAccessedTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
                "FOREIGN KEY(articleID) REFERENCES article(id)",
                "FOREIGN KEY(userID) REFERENCES user(username)"
        );
    }

    public Comment findById(Integer id){
        Comment comment = new Comment();
        comment.setId(id);
        List<Comment> entity = find(comment);
        if(entity.size() == 1){
            return entity.get(0);
        }else{
            return null;
        }
    }

    public List<Comment> findCommentsByUserId(String username){
        Comment comment = new Comment();
        comment.setUserID(username);
        return find(comment);
    }

    public List<Comment> findCommentsByArticleId(Integer id){
        Comment comment = new Comment();
        comment.setArticleID(id);
        return find(comment);
    }
}

