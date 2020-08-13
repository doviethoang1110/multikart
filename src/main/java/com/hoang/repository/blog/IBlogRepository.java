package com.hoang.repository.blog;

import com.hoang.entities.Blog;
import com.hoang.projections.BlogProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog,Integer> {
    @Query(value = "select b.content as content,b.title as title,b.slug as slug,b.image as image,b.createAt as createdAt,b.createBy as createdBy" +
            " from Blog b where b.status = true order by b.updatedAt desc")
    Page<BlogProjection> getAll(Pageable pageable);
    @Query(value = "select b.content as content,b.title as title,b.slug as slug,b.image as image,b.createAt as createdAt,b.createBy as createdBy" +
            " from Blog b where b.slug like:slug")
    BlogProjection findBySlug(@Param("slug") String slug);
    @Query(value = "select b.content as content,b.title as title,b.slug as slug,b.image as image,b.created_at as createdAt,b.created_by as createdBy " +
            "from blog b where b.status = true order by b.updated_at desc limit 5",nativeQuery = true)
    List<BlogProjection> findRecent();
    @Query(value = "select b from Blog b")
    Page<Blog> findAllPaginate(Pageable pageable);
}
