<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<style>
    html {
        height: 100%;
    }
    
    body {
        min-height: 100%;
        display: flex;
        flex-direction: column;
        margin: 0;
    }

    main {
        flex: 1 0 auto;
    }

    .footer {
        background: linear-gradient(135deg, #1a2d4e 0%, #3b5998 100%);
        color: #ffffff;
        padding: 1.5rem 0;
        font-family: 'Roboto', 'Arial', sans-serif;
        width: 100%;
        position: fixed;
        bottom: 0;
        left: 0;
        z-index: 1000;
<<<<<<< HEAD
        position: fixed;
        bottom: 0;
        left: 0;
=======
        box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
>>>>>>> Thanh
    }
    .footer-title {
        font-size: 1.2rem;
        font-weight: 700;
        color: #e0e7ff;
        text-transform: uppercase;
        margin-bottom: 0.5rem;
    }
    .footer-subtitle {
        font-size: 1rem;
        font-weight: 600;
        color: #d1d7f5;
        margin-bottom: 0.5rem;
    }
    .footer-text {
        font-size: 0.85rem;
        color: #b8c2e8;
        margin-bottom: 0.5rem;
    }
    .footer-links {
        margin: 0;
        padding: 0;
    }
    .footer-links li {
        font-size: 0.85rem;
        color: #b8c2e8;
        margin-bottom: 0.3rem;
        transition: color 0.3s ease;
        list-style: none;
    }
    .footer-links a {
        color: #b8c2e8;
        text-decoration: none;
        transition: color 0.3s ease, transform 0.3s ease;
        display: inline-block;
    }
    .footer-links a:hover {
        color: #ffffff;
        transform: translateX(5px);
    }
    .social-links {
        margin-top: 1rem;
    }
    .social-links .social-icon {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        width: 35px;
        height: 35px;
        background-color: rgba(224, 231, 255, 0.1);
        color: #e0e7ff;
        border-radius: 50%;
        margin-right: 0.8rem;
        text-decoration: none;
        transition: all 0.3s ease;
    }
    .social-links .social-icon:hover {
        background-color: #ffffff;
        color: #1a2d4e;
        transform: translateY(-3px);
    }
    .footer-copyright {
        font-size: 0.8rem;
        color: #b8c2e8;
        margin-top: 1.5rem;
        padding-top: 1rem;
        border-top: 1px solid rgba(255, 255, 255, 0.1);
    }
    @media (max-width: 767px) {
        .footer {
            padding: 1rem 0;
        }
        .footer-title {
            margin-top: 1rem;
        }
        .footer-title, .footer-subtitle {
            font-size: 1rem;
        }
        .footer-text, .footer-links li, .footer-copyright {
            font-size: 0.75rem;
        }
        .social-links .social-icon {
            width: 30px;
            height: 30px;
            margin-right: 0.5rem;
        }
    }
</style>
<footer class="footer">
    <div class="container">
        <div class="row gy-4">
            <div class="col-md-4 text-center text-md-start">
                <h4 class="footer-title">PTIT Parking System</h4>
                <p class="footer-text">Hệ thống quản lý bãi gửi xe thông minh.</p>
                <div class="social-links">
                    <a href="#" class="social-icon" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="social-icon" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                    <a href="#" class="social-icon" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <h5 class="footer-subtitle">Liên hệ</h5>
                <ul class="footer-links">
                    <li><i class="fas fa-university me-2"></i> Học Viện Công Nghệ Bưu Chính Viễn Thông</li>
                    <li><i class="fas fa-envelope me-2"></i> hvbcvthcm@ptithcm.edu.vn</li>
                    <li><i class="fas fa-phone me-2"></i> (028) 38.295.258</li>
                    <li><i class="fas fa-map-marker-alt me-2"></i> Số 11 Nguyễn Đình Chiểu, Quận 1, TP.HCM</li>
                </ul>
            </div>
            <div class="col-md-4 text-center text-md-end">
                <h5 class="footer-subtitle">Hỗ trợ</h5>
                <ul class="footer-links">
                    <li><a href="#"><i class="fas fa-book me-2"></i>Hướng dẫn sử dụng</a></li>
                    <li><a href="#"><i class="fas fa-shield-alt me-2"></i>Chính sách bảo mật</a></li>
                    <li><a href="#"><i class="fas fa-headset me-2"></i>Liên hệ hỗ trợ</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-12 text-center">
                <p class="footer-copyright mb-0">© 2025 PTIT Parking System. Designed by PTIT.</p>
            </div>
        </div>
    </div>
</footer>
</div> <!-- Đóng div#page-content từ header -->