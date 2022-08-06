package com.harish.library.handlers;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class JacksonExceptionHandler {

    public String getErrorMessage(HttpMessageNotReadableException e) {
        String message = null;
        boolean handled = false;
        Throwable cause = e.getRootCause();

        if (cause instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException exception = (UnrecognizedPropertyException) cause;
            //message = handleUnrecognizedPropertyException(exception);
            handled = true;
        }
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) cause;
            //message = handleInvalidFormatException(exception);
            handled = true;
        }
        if (cause instanceof MismatchedInputException) {
            if (!handled) {
                MismatchedInputException exception = (MismatchedInputException) cause;
               // message = handleMisMatchInputException(exception);
            }
        }
        if (cause instanceof JsonParseException) {
            message = "Malformed json";
        }
        return message;
    }

//    private String handleInvalidFormatException(InvalidFormatException exception) {
//        String reference = null;
//        if (!exception.getPath().isEmpty()) {
//            String path = extractPropertyReference(exception.getPath());
//            reference = removeLastCharacter(path);
//        }
//        Object value = exception.getValue();
//        return "Invalid value '" + value + "' for property : " + reference;
//    }
//
//    private String handleUnrecognizedPropertyException(UnrecognizedPropertyException exception) {
//        String reference = null;
//        if (!exception.getPath().isEmpty()) {
//            String path = extractPropertyReference(exception.getPath());
//            reference = removeLastCharacter(path);
//        }
//        return "Unknown property : '" + reference + "'";
//    }
//
//    private String handleMisMatchInputException(MismatchedInputException exception) {
//        String reference = null;
//        if (!exception.getPath().isEmpty()) {
//            reference = extractPropertyReference(exception.getPath());
//        }
//        String property = StringUtils.substringBetween(exception.getLocalizedMessage(), "'", "'");
//        // if property missing inside nested object
//        if (reference != null && property!=null) {
//            return "Missing property : '" + reference + property + "'";
//        }
//        // if invalid value given to array
//        if(property==null){
//            return "Invalid values at : '"+ reference +"'";
//        }
//        // if property missing at root level
//        else return "Missing property : '" + property + "'";
//    }
//
    // extract nested object name for which property is missing
//    private String extractPropertyReference(List<JsonMappingException.Reference> path) {
//        StringBuilder stringBuilder = new StringBuilder();
//        path.forEach(reference -> {
//                    if(reference.getFieldName() != null) {
//                        stringBuilder.append(reference.getFieldName()).append(".");
//                        // if field is null means it is array
//                    } else stringBuilder.append("[].");
//                }
//                );
//        return stringBuilder.toString();
//    }
//
//    // remove '.' at the end of property path reference
//    private String removeLastCharacter(String string) {
//        return string.substring(0, string.length() - 1);
//    }
}
