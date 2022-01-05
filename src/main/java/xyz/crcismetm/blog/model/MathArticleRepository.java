package xyz.crcismetm.blog.model;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.database.Repository;

import java.util.List;

public class MathArticleRepository extends Repository<MathArticle> {
    public MathArticleRepository(Database database) {
        super("MathArticle", MathArticle.class, database);
        create(
          "id CHAR(64) PRIMARY KEY",
          "title VARCHAR(126) NOT NULL",
          "subtitle VARCHAR(255) DEFAULT ''",
          "owner CHAR(16) NOT NULL",
          "deleted TINYINT DEFAULT 0",
          "createdTime DATETIME DEFAULT CURRENT_TIMESTAMP",
          "FOREIGN KEY (owner) REFERENCES user(username)"
        );
    }

    public MathArticle findById(String id){
        MathArticle mathArticle = new MathArticle();
        mathArticle.setId(id);
        mathArticle.setDeleted(0);
        List<MathArticle> article = find(mathArticle);
        if(article.size() == 1){
            return article.get(0);
        }
        return null;
    }

    public List<MathArticle> findByDeleted(Integer deleted){
        MathArticle mathArticle = new MathArticle();
        mathArticle.setDeleted(deleted);
        return find(mathArticle);
    }

    public void temporarilyDeleteById(String id){
        MathArticle mathArticle = new MathArticle();
        mathArticle.setDeleted(1);
        update(mathArticle,findById(id));
    }
}
