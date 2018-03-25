package service;

import entity.Lender;

import java.util.List;

public interface ParseService {

    List<Lender> parse(String file);
}
