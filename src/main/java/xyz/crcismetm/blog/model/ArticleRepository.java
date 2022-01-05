package xyz.crcismetm.blog.model;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.database.Repository;

import java.util.List;

public class ArticleRepository extends Repository<Article> {
    private final CommentRepository commentRepository;

    public ArticleRepository(Database database) {
        super("article", Article.class, database);
        create(
                "id INT PRIMARY KEY AUTO_INCREMENT",
                "title VARCHAR(126) NOT NULL",
                "content LONGTEXT NOT NULL",
                "createdTime DATETIME DEFAULT CURRENT_TIMESTAMP",
                "lastAccessedTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
                "subject VARCHAR(255) DEFAULT 'default'",
                "status TINYINT DEFAULT 0",
                "commentable TINYINT DEFAULT 1",
                "userID CHAR(16) NOT NULL",
                "FOREIGN KEY (userID) REFERENCES user(username)"
        );
        commentRepository = new CommentRepository(database);
    }

    public Article findById(Integer id) {
        if (id != null) {
            Article article = new Article();
            article.setId(id);
            List<Article> entity = find(article);
            if (entity.size() == 1) {
                return entity.get(0);
            }
        }
        return null;
    }

    public Article saveOrUpdate(Article article) {
        if (article.getId() != null) {
            Article existingArticle = findById(article.getId());
            if (existingArticle != null) {
                update(article, existingArticle);
            } else {
                save(article);
            }
        }
        return findById(article.getId());
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public List<Comment> findCommentsById(Integer id){
        Comment comment = new Comment();
        comment.setArticleID(id);
        return commentRepository.find(comment);
    }

    public List<Article> findByTitle(String title){
        String preparedSQL = "SELECT * FROM article WHERE title LIKE ?";
        return rawQuery(preparedSQL,"%"+title+"%");
    }
}
