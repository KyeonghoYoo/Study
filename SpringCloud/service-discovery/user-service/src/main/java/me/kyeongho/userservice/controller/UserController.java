package me.kyeongho.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.kyeongho.userservice.dto.UserDto;
import me.kyeongho.userservice.entity.UserEntity;
import me.kyeongho.userservice.service.UserService;
import me.kyeongho.userservice.vo.GreetingProperties;
import me.kyeongho.userservice.vo.RequestUser;
import me.kyeongho.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(path = "/")
@EnableConfigurationProperties({GreetingProperties.class})
@RequiredArgsConstructor
public class UserController {

    private final Environment env; // org.springframework.core.env.Environment 빈 Constructor Autowired로 주입 받기
    private final GreetingProperties greetingProperties;
    private final UserService userService;

    @GetMapping(path = "/health_check")
    public String status() {
        return String.format("it's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping(path = "/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message"); # Environment 빈을 통해 시스템 프로퍼티 가져오기
        return greetingProperties.getMessage();
    }

    /**
     * 사용자 정보 등록 API Controller
     */
    @PostMapping(path = "/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                        .setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(requestUser, UserDto.class);
        UserDto savedUserDto = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(savedUserDto, ResponseUser.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);
    }

    /**
     * 전체 사용자 조회
     */
    @GetMapping(path = "/users")
    public ResponseEntity<List<ResponseUser>> getAllUsers() {
        Iterable<UserEntity> userEntityIterable = userService.getUserByAll();

        List<ResponseUser> responseUsers = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        userEntityIterable
                .forEach(userEntity ->
                        responseUsers.add(mapper.map(userEntity, ResponseUser.class))
                );

        return ResponseEntity.ok(responseUsers);
    }

    /**
     * 사용자 정보, 주문 내역 조회
     */
    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable(name = "userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.ok(responseUser);
    }
}
