#ifdef GL_ES
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
varying vec4 gl_FragCoord;

uniform sampler2D u_texture;
uniform float u_PosX, u_PosY;
uniform float u_TextureWidth, u_TextureHeight;

void main(){
    gl_FragColor = v_color * texture2D(u_texture, v_texCoords);

	if ((gl_FragCoord[0] > u_PosX - u_TextureWidth / 2 ) && (gl_FragCoord[0] < u_PosX + u_TextureWidth / 2)
	&& (gl_FragCoord[1] > u_PosY - u_TextureHeight / 2) && (gl_FragCoord[1] < u_PosY + u_TextureHeight / 2))
		gl_FragColor.rgb=1.0-gl_FragColor.rgb;
}