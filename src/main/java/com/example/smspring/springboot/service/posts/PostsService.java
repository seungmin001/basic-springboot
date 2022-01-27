package com.example.smspring.springboot.service.posts;

import com.example.smspring.springboot.domain.posts.Posts;
import com.example.smspring.springboot.domain.posts.PostsRepository;
import com.example.smspring.springboot.web.dto.PostsListResponseDto;
import com.example.smspring.springboot.web.dto.PostsResponseDto;
import com.example.smspring.springboot.web.dto.PostsSaveRequestDto;
import com.example.smspring.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id)); //entity 존재 확인

        postsRepository.delete(posts); // JPA delete 메소드 활용 . deleteById 도 가능
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // transaction 범위는 유지하되 조회 기능만 남겨두어 속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                // 지정한 타입의 객체 new로 생성하여 mapping // (posts -> new PostsListResponseDto(posts)) 와 같음
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
