package com.boot3.controller;

import com.boot3.data.dto.request.FruitReqDTO;
import com.boot3.data.dto.response.FruitDTO;
import com.boot3.service.FruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FruitGraphqlController {
    private final FruitService fruitService;

    /**
     *  @MutationMapping 은 @PostMapping과 같은 어노테이션으로 graphql에 Mutation에 사용됩니다.
     *  graphql은 endpoint과 하나이므로 @MutationMapping 어노테이션만 지정해 주고 다른 설정은 필요 없습니다.
     */
    @MutationMapping
    public FruitDTO save(@Argument String name) { // @Argument 는 @RequestBody, @RequestParam과 같은 인자값을 지정해줄 때 사용합니다.
        return fruitService.save(name);
    }

    /**
     * @QueryMapping 도 @GetMapping과 같은 어노테이션 입니다.
     * 말고도 @SubscriptionMapping이 있습니다.
     */
    @QueryMapping
    public FruitDTO getFruit(@Argument String name) {
        FruitReqDTO dto = FruitReqDTO.builder()
                .name(name)
                .build();

        return fruitService.getFruit(dto);
    }

    @QueryMapping
    public List<FruitDTO> getFruits() {
        return fruitService.getFruits();
    }
}
