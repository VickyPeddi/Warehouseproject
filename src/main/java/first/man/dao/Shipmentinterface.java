package first.man.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import first.man.model.Shipmentmodel;

@Repository
public interface Shipmentinterface extends JpaRepository<Shipmentmodel, Integer> {

	@Query("select count(st.shipmentcode) from  Shipmentmodel st  where st.shipmentcode= ?1")
	int getshipmentnoofshipmentmode(String shipmentcode);

	@Query("select shipid,shipmentcode from Shipmentmodel")
	public List<Object[]> getidandshipmentcode();

	@Query("select shipmentmode,count (shipmentmode)from Shipmentmodel group by shipmentmode")
	List<Object[]> getShipmentmodecount();
}
