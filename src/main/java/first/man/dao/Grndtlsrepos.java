package first.man.dao;

import first.man.model.Grndtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Grndtlsrepos extends JpaRepository<Grndtl,Integer> {
}
