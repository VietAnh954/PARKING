package vn.bacon.parking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.EntryExitDetail;

public interface EntryExitDetailRepository extends JpaRepository<EntryExitDetail, Integer> {

    Page<EntryExitDetail> findByTgRaIsNullAndNvRaIsNull(Pageable pageable);
}