
# Sử dụng một image cơ sở có sẵn có hỗ trợ Java, ví dụ: OpenJDK
FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.20_8

LABEL maintainer="khaphpdz@gmail.com"
LABEL Author="KhaPHP"

# Tạo thư mục app trong container
WORKDIR /app

# Sao chép tất cả các tệp tin .jar đã được đóng gói từ dự án Spring Boot vào thư mục /app trong container
COPY target/*.jar app.jar

# Có thể cần các bước khác để cấu hình và chạy ứng dụng (tùy thuộc vào yêu cầu của dự án)
ENV PORT=5000
ENV AUTHOR=Pham_Huynh_Phuong_Kha

# Chạy ứng dụng Spring Boot khi container được khởi chạy
CMD ["java", "-jar", "app.jar"]
