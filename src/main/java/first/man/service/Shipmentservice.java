package first.man.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import first.man.dao.Shipmentinterface;
import first.man.model.Shipmentmodel;

@Service
@Transactional
public class Shipmentservice {
	@Autowired
	private Shipmentinterface repos;

	public List<Shipmentmodel> getall() {
		return repos.findAll();
	}

	public Shipmentmodel getbyid(int id) throws Exception {
		Optional<Shipmentmodel> model = repos.findById(id);
		if (model.isPresent()) {
			return model.get();
		} else {
			throw new Exception("no records found of this id");
		}

	}

	public Shipmentmodel createorupdate(Shipmentmodel model) {
		return repos.save(model);
	}

	public void deletebyid(int id) {
		Shipmentmodel shipmentmodel = this.repos.findById(id).get();
		System.err.println("delted bt id " + id);
		repos.delete(shipmentmodel);

	}

	public boolean shipmentcodeexists(String shipmentcode) {
		int count = repos.getshipmentnoofshipmentmode(shipmentcode);
		boolean exists = count > 0 ? true : false;
		return exists;
	}

	public Map<Integer, String> getidandshipmentcode() {
		List<Object[]> list = repos.getidandshipmentcode();
		Map<Integer, String> map = repos.getidandshipmentcode().stream()
				.collect(Collectors.toMap(arr -> Integer.valueOf(arr[0].toString()), arr -> arr[1].toString()));
		return map;
	}

	public List<Object[]> getshipmentmodecount()
	{
		return repos.getShipmentmodecount();
	}
}
