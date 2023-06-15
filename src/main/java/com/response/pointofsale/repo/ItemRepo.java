package com.response.pointofsale.repo;

import com.response.pointofsale.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@EnableJpaRepositories
@Repository
@Transactional
public interface ItemRepo extends JpaRepository<Item,Integer> {

    @Modifying // update query
    @Query(value = "update item set balance_qty=?1, selling_price=?2, active_state=?3 where item_id=?4", nativeQuery = true)
    void updateItem(double balanceQty, double sellingPrice, boolean activeState, int id);

    @Modifying // delete query
    @Query(value = "delete from item where item_id=?1",nativeQuery = true)
    void deleteItem(int id);

    List<Item> findAllByActiveStateEquals(Boolean b);

    int countAllByActiveStateEquals(boolean b);

    Page<Item> findAllByActiveStateEquals(boolean b, Pageable pageable);


}
