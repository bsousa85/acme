package com.isep.acme.model;

import com.isep.acme.model.user.BaseUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T01:41:02+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.20 (GraalVM Community)"
)
@Component
public class UserViewMapperImpl extends UserViewMapper {

    @Override
    public UserView toUserView(BaseUser user) {
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
