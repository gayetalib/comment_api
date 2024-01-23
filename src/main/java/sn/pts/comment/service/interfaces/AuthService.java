package sn.pts.comment.service.interfaces;

import sn.pts.comment.web.dto.request.auth.SigninRequestDTO;
import sn.pts.comment.web.dto.request.auth.SignupRequestDTO;

import java.util.Map;

public interface AuthService {
    Map<String, String> signup(SignupRequestDTO dto);
    Map<String, String> signin(SigninRequestDTO dto);
}
