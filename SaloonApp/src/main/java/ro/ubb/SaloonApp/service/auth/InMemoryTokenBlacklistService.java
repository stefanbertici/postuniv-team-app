package ro.ubb.SaloonApp.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InMemoryTokenBlacklistService {

    private final Set<String> tokens = new HashSet<>();

    public void blacklist(String token) {
        tokens.add(token);
    }

    public boolean isBlackListed(String token) {
        return tokens.contains(token);
    }
}
