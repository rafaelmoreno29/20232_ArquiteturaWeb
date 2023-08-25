package com.example.aula3.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.aula3.models.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CursoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Curso inserir(Curso curso) {
        entityManager.merge(curso);
        return curso;
    }

    public List<Curso> obterTodos() {
        return entityManager.createQuery("from Curso",
                Curso.class).getResultList();
    }

    public List<Curso> obterPorNome(String nome) {
        String jpql = " select c from Curso c where c.nome like :nome";
        TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

}
