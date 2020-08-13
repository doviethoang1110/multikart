package com.hoang.repository.banner;

import com.hoang.dto.client.BannerDto;
import com.hoang.entities.Banner;
import com.hoang.projections.BannerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBannerRepository extends JpaRepository<Banner,Integer> {
    @Query(value = "select b.id as id,b.title as title,b.content as content,b.status as status,b.type as type,b.image as image " +
            "from Banner b")
    Page<BannerProjection> getAll(Pageable pageable);
    @Query(value = "select new com.hoang.dto.client.BannerDto(b.id,b.title,b.content,b.image,b.links,b.type) " +
            "from Banner b where b.status = true")
    List<BannerDto> findForIndex();
}
