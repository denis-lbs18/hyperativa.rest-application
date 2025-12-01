package com.hyperativa.rest_application.builder.entities;

import com.hyperativa.rest_application.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Sets;

import java.time.LocalDate;
import java.util.HashSet;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.ANY_STRING;
import static com.hyperativa.rest_application.builder.ConstantsBuilder.UPDATED_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserBuilder {
    public static User buildUser() {
        return User.builder()
                .id(0)
                .fullName(ANY_STRING)
                .email(ANY_STRING)
                .password(ANY_STRING)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .authorities(Sets.newHashSet())
                .build();
    }

    public static Iterable<User> buildUserList() {
        User user1 = User.builder()
                .id(1)
                .fullName(ANY_STRING + "1")
                .email(ANY_STRING + "1")
                .password(ANY_STRING)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .authorities(Sets.newHashSet())
                .build();

        User user2 = User.builder()
                .id(2)
                .fullName(ANY_STRING + "2")
                .email(ANY_STRING + "2")
                .password(ANY_STRING)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .authorities(Sets.newHashSet())
                .build();

        return new HashSet<>() {{
            add(user1);
            add(user2);
        }};
    }

    public static User buildUpdatedUser() {
        return User.builder()
                .id(0)
                .fullName(UPDATED_NAME)
                .email(ANY_STRING)
                .password(ANY_STRING)
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .authorities(Sets.newHashSet())
                .build();
    }
}
