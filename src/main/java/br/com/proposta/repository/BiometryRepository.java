package br.com.proposta.repository;

import br.com.proposta.biometry.Biometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BiometryRepository extends JpaRepository<Biometry, Long> {
}
