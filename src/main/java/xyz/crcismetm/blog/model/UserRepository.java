package xyz.crcismetm.blog.model;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.database.Repository;

import java.util.List;

public class UserRepository extends Repository<User> {
    private ArticleRepository articleRepository;

    public UserRepository(Database database) {
        super("user", User.class, database);
        create(
                "username CHAR(16) PRIMARY KEY",
                "email VARCHAR(126) NOT NULL",
                "password VARCHAR(16) NOT NULL",
                "gender TINYINT DEFAULT 0",
                "role TINYINT DEFAULT 0",
                "status TINYINT DEFAULT 0",
                "createdTime DATETIME DEFAULT CURRENT_TIMESTAMP"
        );
        articleRepository = new ArticleRepository(database);
    }

    public User findById(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> entity = find(user);
        if (entity.size() == 1) {
            return entity.get(0);
        }
        return null;
    }

    public User saveOrUpdate(User user) {
        if (user.getUsername() != null) {
            User existingUser = findById(user.getUsername());
            if (existingUser != null) {
                update(user, existingUser);
            } else {
                save(user);
            }
        }
        return findById(user.getUsername());
    }

    public User updateById(User user, String username) {
        User oldUser = new User();
        oldUser.setUsername(username);
        update(user, oldUser);
        return findById(username);
    }

    public ArticleRepository getArticleRepository(){
        return articleRepository;
    }

    public List<Article> findArticlesById(String username){
        Article article = new Article();
        article.setUserID(username);
        return articleRepository.find(article);
    }
}
