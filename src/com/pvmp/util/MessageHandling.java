package com.pvmp.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;



public class MessageHandling {
	public static final String INVALID_NAME = "Nome inválido.";
	public static final String NAME_LENGHT = "Seu nome deve ter de 3 a 50 caracteres.";
	public static final String EMAIL_FORMAT = "Formato de email inválido.";
	public static final String EMAIL_LENGHT = "Seu email deve ter no máximo 40 caracteres.";
	public static final String AGE_RANGE = "Sua idade tem de estar entre o intervalo 10-99.";
	public static final String PASSWORD_LENGHT = "Sua senha deve ter de 6 a 15 caracteres.";
	public static final String PASSWORD_FORMAT = "Sua senha deve ser formada apenas por letras e números.";
	public static final String PASSWORD_NOT_MATCH = "Senha antiga não correspondente.";
	public static final String EXISTING_EMAIL = "Email já existente";
	public static final String EXISTING_USERNAME = "Nome de usuário já existente";
	public static final String USERNAME_LENGHT  = "Seu nome de usuário deve ter de 3 a 15 caracteres";
	public static final String USERNAME_FIRST_CHAR = "Seu nome de usuário deve começar com uma letra.";
	public static final String USERNAME_FORMAT = "Seu nome de usuário deve ser composto apenas de letras e números.";
	public static final String ERROR_LOGIN = "Nome de usuário e/ou senha não correspondem";
	public static final String PASSWORD_CONFIRM_NOT_MATCH = "As duas senhas não correspondem";
	
	public static final String SUCCESSFUL_REGISTER = "Cadastro realizado com sucesso!";
	public static final String PASSWORD_SUCCESSFUL_CHANGE = "Senha alterada com sucesso.";
	public static final String PASSWORD_CONFIRM_TO_DELETE = "Digite sua senha para poder excluir.";
	public static final String SUCCESSFUL_DELETE = "Conta excluida com sucesso.";
	public static final String GUEST_SETTING_WARNING = "Você deve estar logado para poder acessar essa área";
	
	private static Toast toast = null;
	
	/**
	 * @param text
	 * @param context
	 */
	public static void showToast (CharSequence text, Context context) {
		int duration = Toast.LENGTH_LONG;
		if (toast == null) 
		{
			toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
		}
		if(!toast.getView().isShown())
		{
			toast.setText(text);
			toast.show();
		}
	}
	
	/**
	 * @param editText
	 */
	public static void requestAttention (EditText editText) {
		editText.setText("");
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
	}
	
	/**
	 * @param editText
	 * @param text
	 * @param context
	 */
	public static void genericError (EditText editText, CharSequence text, Context context) {
		if (editText != null) {
			showToast(text, context);
			requestAttention (editText);
		}
	}
	
	/** 
	 * @param et1
	 * @param et2
	 * @param et3
	 * @param et4
	 * @param validationResult
	 * @param context
	 */
	public static void displayEditError (EditText et1, EditText et2, EditText et3,
										 EditText et4, int validationResult,
										 Context context) {
		switch (validationResult) {
			case 1:
				MessageHandling.genericError(et2, INVALID_NAME, context);
				break;
			case 2:
				MessageHandling.genericError(et2, NAME_LENGHT, context);
				break;
			case 3:
				MessageHandling.genericError(et1, EMAIL_FORMAT, context);
				break;
			case 4:				
				MessageHandling.genericError(et1, EMAIL_LENGHT, context);
				break;
			case 5:
				MessageHandling.genericError(et4, AGE_RANGE, context);
				break;
			case 6:
				MessageHandling.genericError(et3, PASSWORD_LENGHT, context);
				break;
		}
	}
	
	/**
	 * @param et1
	 * @param et2
	 * @param et3
	 * @param et4
	 * @param et5
	 * @param validationResult
	 * @param context
	 */
	public static void displayRegisterError(EditText et1, EditText et2, EditText et3,
										    EditText et4, EditText et5, int validationResult,
										    Context context) {
	    displayEditError(et2, et3, et4, et5, validationResult, context);
	    if (validationResult > 6) {
			switch (validationResult) {
				case 7:
					MessageHandling.genericError(et2, EXISTING_EMAIL, context);
					break;
				case 8:
					MessageHandling.genericError(et1, EXISTING_USERNAME, context);
					break;
				case 9:
					MessageHandling.genericError(et1, USERNAME_LENGHT, context);
					break;
				case 10:
					MessageHandling.genericError(et1, USERNAME_FIRST_CHAR, context);
					break;
				case 11:
					MessageHandling.genericError(et1, USERNAME_FORMAT, context);
					break;
				case 12:
					MessageHandling.genericError(et3, PASSWORD_FORMAT, context);
					break;
		    }
	    }
	}
}
