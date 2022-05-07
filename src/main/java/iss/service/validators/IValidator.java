package iss.service.validators;

public interface IValidator<E> {
    void validate(E entity);
}
