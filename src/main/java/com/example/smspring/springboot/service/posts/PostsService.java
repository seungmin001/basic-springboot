package com.example.smspring.springboot.service.posts;

import com.example.smspring.springboot.domain.posts.Posts;
import com.example.smspring.springboot.domain.posts.PostsRepository;
import com.example.smspring.springboot.web.dto.PostsResponseDto;
import com.example.smspring.springboot.web.dto.PostsSaveRequestDto;
import com.example.smspring.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId(); // 등록 후 id 반환
    }

    @Transactional // 처리하지 않은 오류 발생 시 rollback 지원
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id)); //해당 id 게시글 찾기

        posts.update(requestDto.getTitle(), requestDto.getContent());

        // update 쿼리를 하지 않는 이유 : 더티 체킹 : (JPA 영속성 컨텍스트 유지 상태에서 데이터 변경 시 트랜잭션 종료 후 반영)

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
