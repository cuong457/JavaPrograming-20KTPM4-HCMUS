                   while (current_chat_data == null) {
                        try {
                            Thread.sleep(100);
                        } catch(InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }