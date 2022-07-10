package com.gim.repo;

import com.BaseDao;
import com.gim.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @author Gim
 */
@Repository
public interface StudentRepo  extends BaseDao<Student> {
}
