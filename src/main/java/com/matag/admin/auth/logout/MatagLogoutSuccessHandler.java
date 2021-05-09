package com.matag.admin.auth.logout;

import static com.matag.admin.session.AuthSessionFilter.SESSION_NAME;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.matag.admin.session.MatagSessionRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MatagLogoutSuccessHandler implements LogoutSuccessHandler {
  private final MatagSessionRepository matagSessionRepository;

  @Transactional
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    var sessionId = request.getHeader(SESSION_NAME);
    if (StringUtils.hasText(sessionId)) {
      matagSessionRepository.deleteBySessionId(sessionId);
    }

    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().flush();
  }
}
