package org.qa.userproducerapp.dal;

import org.qa.userproducerapp.model.User;

import java.util.List;

public interface UserDAL {
    List<User> getAll();
}
