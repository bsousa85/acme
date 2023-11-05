package com.isep.acme.model;

import com.isep.acme.model.user.BaseUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(BaseUser user);
}
