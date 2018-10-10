package com.blogengine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blogengine.domain.Blogger;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, Long> {

	/** 
	 * Visszaadja az összes Bloggert az adatbázisból.
	 * Ha nincs az adatbázisban Blogger, akkor üres Listával tér vissza.
	 * */
	public List<Blogger> findAll();
	
	/** 
	 * Visszaadja azt a Bloggert, akinek az EmailCíme megegyezik a paraméterben megadottal.
	 * Ha többet talál, az elsőt fogja visszaadni.
	 * Ha nem talál ilyet, akkor null értékű objektumot fog visszaadni.
	 * */
	public Optional<Blogger> findFirst1ByEmailAddress(String email);
	
	/** 
	 * Visszaadja azt a Bloggert, akinek az UserName megegyezik a paraméterben megadottal.
	 * Ha többet talál, akkor az elsőt fogja visszaadni.
	 * Ha nem talál ilyet, akkor null értékű objektumot fog visszaadni.
	 * */
	public Optional<Blogger> findFirst1ByUserName(String username);
}
