package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.bacon.parking.domain.EntryExitDetail;

public interface EntryExitDetailRepository extends JpaRepository<EntryExitDetail, Integer> {
}