package sn.pts.comment.service.implementations;

import org.springframework.stereotype.Service;
import sn.pts.comment.service.interfaces.AuthService;
import sn.pts.comment.web.dto.request.auth.SigninRequestDTO;
import sn.pts.comment.web.dto.request.auth.SignupRequestDTO;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public Map<String, String> signup(SignupRequestDTO dto) {
        return null;
    }

    @Override
    public Map<String, String> signin(SigninRequestDTO dto) {
        return null;
    }
}
