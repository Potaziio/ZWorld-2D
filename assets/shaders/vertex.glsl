#version 330 core

layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;

uniform mat4 view;
uniform mat4 projection;
uniform mat4 model;

out vec4 fragColor;

void main() {
    gl_Position = projection * view * model * vec4(aPos, 1.0);
    fragColor = aColor;
}
