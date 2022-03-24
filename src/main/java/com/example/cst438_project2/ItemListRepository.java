package com.example.cst438_project2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemListRepository extends CrudRepository<ItemList,Integer> {

    ItemList findByName(String name);

    /*
    @Query("SELECT * FROM ItemList l WHERE l.user_id =?1")
    List<ItemList> findListsByUserId(Integer id);
   */
    boolean existsByName(String name);

    @Query(value = "SELECT * FROM ItemList l WHERE l.user_id LIKE %:id%",
            nativeQuery = true)
    List<ItemList> findListsByUserId(@Param("id") Integer id);


}
