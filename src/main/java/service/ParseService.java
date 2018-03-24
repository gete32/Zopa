package service;

import entity.Lender;

import java.util.Set;

public interface ParseService {

    Set<Lender> parse(String file);
}
