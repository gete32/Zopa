package converter;

import exception.InvalidCsvValueException;

public interface Converter<S, T, M> {

    T convert(S source, M header) throws InvalidCsvValueException;

}
