package iss.repository;

import java.util.List;

public interface IUserRepository<E> {
    E find (Integer id) throws Exception;
    void add (E entity) throws Exception;
    E delete (Integer id) throws Exception;
    List<E> getAll() throws Exception;
}
