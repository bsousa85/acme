package com.isep.acme.model;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-12T22:10:38-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class UserViewMapperImpl extends UserViewMapper {

    @Override
    public UserView toUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        userView.setFullName( user.getFullName() );
        if ( user.getUserId() != null ) {
            userView.setUserId( String.valueOf( user.getUserId() ) );
        }
        userView.setUsername( user.getUsername() );

        return userView;
    }
}
