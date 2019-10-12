package s4.spring.ds1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.ds1.models.Todos;

public interface TodosRepository extends JpaRepository<Todos,Integer > {
	public List<Todos> findByName(String name);
}
