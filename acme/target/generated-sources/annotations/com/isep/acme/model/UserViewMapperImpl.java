package com.isep.acme.model;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-10T22:59:21+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_382 (Azul Systems, Inc.)"
)
@Component
public class UserViewMapperImpl extends UserViewMapper {

    @Override
    public UserView toUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        if ( user.getUserId() != null ) {
            userView.setUserId( String.valueOf( user.getUserId() ) );
        }
        userView.setUsername( user.getUsername() );
        userView.setFullName( user.getFullName() );

        return userView;
    }
}
