package bookserver.manager;

import bookserver.model.TokenModel;

public interface TokenManager {
    TokenModel createToken(long userId);

    boolean checkToken(TokenModel model);

    TokenModel getToken(String authentication);

    void deleteToken(long userId);
}
