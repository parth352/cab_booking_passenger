package myproject.example.myproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myproject.example.myproject.entity.Position;

@Repository
public interface PositionDao extends JpaRepository<Position,Long>{

	Position findById(long id);

	
	

}
