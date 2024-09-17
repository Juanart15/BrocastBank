import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import './Mainpage.css'; 
import logoBB from './logoBB.png';

function MainPage({ clientName }) {
  const navigate = useNavigate(); 

  return (
    
    <div className="main-container">
       
      <div className="left-container">
      <div className="stars">
        {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
         {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
      </div>
        <h2>{clientName}</h2>
        <ul>
        <div className="stars">
        {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
         {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
      </div>
          <li onClick={() => navigate('/CreateAccount')}>Crear Cuenta <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAACXBIWXMAAAsTAAALEwEAmpwYAAAFPUlEQVR4nO1bW4ydUxTeLWaIS10fBNW0jUuDJiTqFnV9dPcgZSRS6SjqUhXT4aE8keBJlbZBpZEQl2TqRQaJUmQ6xZNiEH3iQYWOzqj9LZ+s//yd2f/uOWf+c87e5/w/8yU7OcnZe621v31be/1rGzONaUwjJkgeTPICAisp8iJFPiQwQuAXAnvSor9HKPJBUgd4kOQibWvKCJJHELiVIlsI/EkRNlWAUYoMEFiiMk3RQXI+RdYR2Nt0p2uToUQ+T3KeKRpInkKR1wighvHfUGQ9gV5ae6V2guQxJA9Ji/6el/wH3EWRDQS+rSFLdWwmeXIROt5F4NGqIw58QeAhJacF+bMJrCLwZdUZAfQrgWF7lRMkTyXwqWfUP8m6t/aqCPouocgbB8wyYLsuvdD66oLW3kDgD8+QIZLnm8hIT5Ttnu7fae31sXUnSNaoOwrAOIF7Sc40bYLqIrAi0e3uDUBvXMVAv8f81yTPjqq0nj3kwnSDdW3qj6MMWO4p+ozkcVGUNWKXniDAR55t94dVYu3NBMRRMkDyUFMQqC2JTZMECK29KZTw+ZkNT9kuUOczJABbM14keUYIoV95a/5oU1Cky2FyT1D/oZXBosjjmd2eXGgKDpJnERhzluuaVqb+uEPAclMSJMdyduAavz9Q5G1HyOftPOcD+QlDzix4q1EBC1K3trKjkueZkkG9Uq8PC/I3FtnksPeOKSkyR6PIpnyNyFmZTYS80JQUSVQpuxdMfYIRuNO9aZmSg8AOpz9Lp24g8n40l7K2zifSiM/TJGcElV2JRe5fBoN5HJ/xibs9eWJIY+oYOeoY+UJIEjRy5C2D2o4Rrb3M9fpCGTGlkSLPehGfsCS44TVycb2KfY4R60IZMKWB5Iyk05FISMPx+we2r17Fl52KcYML1Ul4ziNhYwgHjMDdjsyX6lXcNlHR2stNmxGLBI1ROgP7Se2KwC5nrcwxHUCM5UByrkPAT7UrArsdAo5t0RfvIfBIk6WPwM+hSNDIlUPAr/UI2OcQ0NU0AcDtB8TxQxRgSZMEdDky9rWDgJ5IBNwSm4DdAZfAbRR5ssnyFIHfOrEEdv3fN8FtzjF4helM59d6nd8Q4Bi82iHg4yI7QmtDd75RR6jPnXatKi6QK7w+nytMLnYq7gyhPKeBz0S+DH3n7G2XNnIdPimUER28Ds92BnWMZHf9BiKDToOVoQzJERAZjRQQWeWQ+16eBksdAnaYkiPJVJnszx1TNyCP8oKiF5uSIkmmyEaDZuVrKPKKM20GTEmRpOvkOf58kDxz4nN4ZTNcZMoYEs9+GDm9MQEibzrTZ5jkQaYkSO8iw87ov96MkLnex9EHTElA4D7v6GvuXkORNY6gv3VTMQWH5ixlNnHgsVaEdXvHyPckjzcFhV7hM15fZel2h06RGSpi4jLJw/Sm59i5h+RpYYRbe6OXJDVI8nBT3CQp0NrrwioBer3wlM6EE4IqaT4vaKtn24o4yoDVnqIRkudGUZbHHvIcvbV6Nq2OqxTo9VJl/9KvyB1Ilb2nSqrssvYYYO21SYJylvnhdiRTpB7eUJVk6Wti666Wyz8ZQ8xukMEvULrUKPJqjXT5zrwiob720ATqau+CdEZockILLzuSb/sqw0+PL8KDiSpJCJvrPJnZmb4GW6bRZnVN02cyM9Oiv+ck/2mdSt3s5lbEJzM+knc/8R5NjaXhsuI9mqrxbE6/DL3b4rO5vamMHpJHmjKClVdhF6WxuY1JtjnwQ/LpSy9YlaK/f0zz/rXOw0mbIqzvaUzD/KfxL78i3f2lW75FAAAAAElFTkSuQmCC"></img></li>
          <li onClick={() => navigate('/ViewAccounts')}>Ver Cuenta <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAACXBIWXMAAAsTAAALEwEAmpwYAAAFPUlEQVR4nO1bW4ydUxTeLWaIS10fBNW0jUuDJiTqFnV9dPcgZSRS6SjqUhXT4aE8keBJlbZBpZEQl2TqRQaJUmQ6xZNiEH3iQYWOzqj9LZ+s//yd2f/uOWf+c87e5/w/8yU7OcnZe621v31be/1rGzONaUwjJkgeTPICAisp8iJFPiQwQuAXAnvSor9HKPJBUgd4kOQibWvKCJJHELiVIlsI/EkRNlWAUYoMEFiiMk3RQXI+RdYR2Nt0p2uToUQ+T3KeKRpInkKR1wighvHfUGQ9gV5ae6V2guQxJA9Ji/6el/wH3EWRDQS+rSFLdWwmeXIROt5F4NGqIw58QeAhJacF+bMJrCLwZdUZAfQrgWF7lRMkTyXwqWfUP8m6t/aqCPouocgbB8wyYLsuvdD66oLW3kDgD8+QIZLnm8hIT5Ttnu7fae31sXUnSNaoOwrAOIF7Sc40bYLqIrAi0e3uDUBvXMVAv8f81yTPjqq0nj3kwnSDdW3qj6MMWO4p+ozkcVGUNWKXniDAR55t94dVYu3NBMRRMkDyUFMQqC2JTZMECK29KZTw+ZkNT9kuUOczJABbM14keUYIoV95a/5oU1Cky2FyT1D/oZXBosjjmd2eXGgKDpJnERhzluuaVqb+uEPAclMSJMdyduAavz9Q5G1HyOftPOcD+QlDzix4q1EBC1K3trKjkueZkkG9Uq8PC/I3FtnksPeOKSkyR6PIpnyNyFmZTYS80JQUSVQpuxdMfYIRuNO9aZmSg8AOpz9Lp24g8n40l7K2zifSiM/TJGcElV2JRe5fBoN5HJ/xibs9eWJIY+oYOeoY+UJIEjRy5C2D2o4Rrb3M9fpCGTGlkSLPehGfsCS44TVycb2KfY4R60IZMKWB5Iyk05FISMPx+we2r17Fl52KcYML1Ul4ziNhYwgHjMDdjsyX6lXcNlHR2stNmxGLBI1ROgP7Se2KwC5nrcwxHUCM5UByrkPAT7UrArsdAo5t0RfvIfBIk6WPwM+hSNDIlUPAr/UI2OcQ0NU0AcDtB8TxQxRgSZMEdDky9rWDgJ5IBNwSm4DdAZfAbRR5ssnyFIHfOrEEdv3fN8FtzjF4helM59d6nd8Q4Bi82iHg4yI7QmtDd75RR6jPnXatKi6QK7w+nytMLnYq7gyhPKeBz0S+DH3n7G2XNnIdPimUER28Ds92BnWMZHf9BiKDToOVoQzJERAZjRQQWeWQ+16eBksdAnaYkiPJVJnszx1TNyCP8oKiF5uSIkmmyEaDZuVrKPKKM20GTEmRpOvkOf58kDxz4nN4ZTNcZMoYEs9+GDm9MQEibzrTZ5jkQaYkSO8iw87ov96MkLnex9EHTElA4D7v6GvuXkORNY6gv3VTMQWH5ixlNnHgsVaEdXvHyPckjzcFhV7hM15fZel2h06RGSpi4jLJw/Sm59i5h+RpYYRbe6OXJDVI8nBT3CQp0NrrwioBer3wlM6EE4IqaT4vaKtn24o4yoDVnqIRkudGUZbHHvIcvbV6Nq2OqxTo9VJl/9KvyB1Ilb2nSqrssvYYYO21SYJylvnhdiRTpB7eUJVk6Wti666Wyz8ZQ8xukMEvULrUKPJqjXT5zrwiob720ATqau+CdEZockILLzuSb/sqw0+PL8KDiSpJCJvrPJnZmb4GW6bRZnVN02cyM9Oiv+ck/2mdSt3s5lbEJzM+knc/8R5NjaXhsuI9mqrxbE6/DL3b4rO5vamMHpJHmjKClVdhF6WxuY1JtjnwQ/LpSy9YlaK/f0zz/rXOw0mbIqzvaUzD/KfxL78i3f2lW75FAAAAAElFTkSuQmCC"></img></li>
          <li onClick={() => navigate('/transaction')}>Transacción <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAACXBIWXMAAAsTAAALEwEAmpwYAAAFPUlEQVR4nO1bW4ydUxTeLWaIS10fBNW0jUuDJiTqFnV9dPcgZSRS6SjqUhXT4aE8keBJlbZBpZEQl2TqRQaJUmQ6xZNiEH3iQYWOzqj9LZ+s//yd2f/uOWf+c87e5/w/8yU7OcnZe621v31be/1rGzONaUwjJkgeTPICAisp8iJFPiQwQuAXAnvSor9HKPJBUgd4kOQibWvKCJJHELiVIlsI/EkRNlWAUYoMEFiiMk3RQXI+RdYR2Nt0p2uToUQ+T3KeKRpInkKR1wighvHfUGQ9gV5ae6V2guQxJA9Ji/6el/wH3EWRDQS+rSFLdWwmeXIROt5F4NGqIw58QeAhJacF+bMJrCLwZdUZAfQrgWF7lRMkTyXwqWfUP8m6t/aqCPouocgbB8wyYLsuvdD66oLW3kDgD8+QIZLnm8hIT5Ttnu7fae31sXUnSNaoOwrAOIF7Sc40bYLqIrAi0e3uDUBvXMVAv8f81yTPjqq0nj3kwnSDdW3qj6MMWO4p+ozkcVGUNWKXniDAR55t94dVYu3NBMRRMkDyUFMQqC2JTZMECK29KZTw+ZkNT9kuUOczJABbM14keUYIoV95a/5oU1Cky2FyT1D/oZXBosjjmd2eXGgKDpJnERhzluuaVqb+uEPAclMSJMdyduAavz9Q5G1HyOftPOcD+QlDzix4q1EBC1K3trKjkueZkkG9Uq8PC/I3FtnksPeOKSkyR6PIpnyNyFmZTYS80JQUSVQpuxdMfYIRuNO9aZmSg8AOpz9Lp24g8n40l7K2zifSiM/TJGcElV2JRe5fBoN5HJ/xibs9eWJIY+oYOeoY+UJIEjRy5C2D2o4Rrb3M9fpCGTGlkSLPehGfsCS44TVycb2KfY4R60IZMKWB5Iyk05FISMPx+we2r17Fl52KcYML1Ul4ziNhYwgHjMDdjsyX6lXcNlHR2stNmxGLBI1ROgP7Se2KwC5nrcwxHUCM5UByrkPAT7UrArsdAo5t0RfvIfBIk6WPwM+hSNDIlUPAr/UI2OcQ0NU0AcDtB8TxQxRgSZMEdDky9rWDgJ5IBNwSm4DdAZfAbRR5ssnyFIHfOrEEdv3fN8FtzjF4helM59d6nd8Q4Bi82iHg4yI7QmtDd75RR6jPnXatKi6QK7w+nytMLnYq7gyhPKeBz0S+DH3n7G2XNnIdPimUER28Ds92BnWMZHf9BiKDToOVoQzJERAZjRQQWeWQ+16eBksdAnaYkiPJVJnszx1TNyCP8oKiF5uSIkmmyEaDZuVrKPKKM20GTEmRpOvkOf58kDxz4nN4ZTNcZMoYEs9+GDm9MQEibzrTZ5jkQaYkSO8iw87ov96MkLnex9EHTElA4D7v6GvuXkORNY6gv3VTMQWH5ixlNnHgsVaEdXvHyPckjzcFhV7hM15fZel2h06RGSpi4jLJw/Sm59i5h+RpYYRbe6OXJDVI8nBT3CQp0NrrwioBer3wlM6EE4IqaT4vaKtn24o4yoDVnqIRkudGUZbHHvIcvbV6Nq2OqxTo9VJl/9KvyB1Ilb2nSqrssvYYYO21SYJylvnhdiRTpB7eUJVk6Wti666Wyz8ZQ8xukMEvULrUKPJqjXT5zrwiob720ATqau+CdEZockILLzuSb/sqw0+PL8KDiSpJCJvrPJnZmb4GW6bRZnVN02cyM9Oiv+ck/2mdSt3s5lbEJzM+knc/8R5NjaXhsuI9mqrxbE6/DL3b4rO5vamMHpJHmjKClVdhF6WxuY1JtjnwQ/LpSy9YlaK/f0zz/rXOw0mbIqzvaUzD/KfxL78i3f2lW75FAAAAAElFTkSuQmCC"></img></li>
          <li onClick={() => navigate('/products')}>Nuestros Productos <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAACXBIWXMAAAsTAAALEwEAmpwYAAAFPUlEQVR4nO1bW4ydUxTeLWaIS10fBNW0jUuDJiTqFnV9dPcgZSRS6SjqUhXT4aE8keBJlbZBpZEQl2TqRQaJUmQ6xZNiEH3iQYWOzqj9LZ+s//yd2f/uOWf+c87e5/w/8yU7OcnZe621v31be/1rGzONaUwjJkgeTPICAisp8iJFPiQwQuAXAnvSor9HKPJBUgd4kOQibWvKCJJHELiVIlsI/EkRNlWAUYoMEFiiMk3RQXI+RdYR2Nt0p2uToUQ+T3KeKRpInkKR1wighvHfUGQ9gV5ae6V2guQxJA9Ji/6el/wH3EWRDQS+rSFLdWwmeXIROt5F4NGqIw58QeAhJacF+bMJrCLwZdUZAfQrgWF7lRMkTyXwqWfUP8m6t/aqCPouocgbB8wyYLsuvdD66oLW3kDgD8+QIZLnm8hIT5Ttnu7fae31sXUnSNaoOwrAOIF7Sc40bYLqIrAi0e3uDUBvXMVAv8f81yTPjqq0nj3kwnSDdW3qj6MMWO4p+ozkcVGUNWKXniDAR55t94dVYu3NBMRRMkDyUFMQqC2JTZMECK29KZTw+ZkNT9kuUOczJABbM14keUYIoV95a/5oU1Cky2FyT1D/oZXBosjjmd2eXGgKDpJnERhzluuaVqb+uEPAclMSJMdyduAavz9Q5G1HyOftPOcD+QlDzix4q1EBC1K3trKjkueZkkG9Uq8PC/I3FtnksPeOKSkyR6PIpnyNyFmZTYS80JQUSVQpuxdMfYIRuNO9aZmSg8AOpz9Lp24g8n40l7K2zifSiM/TJGcElV2JRe5fBoN5HJ/xibs9eWJIY+oYOeoY+UJIEjRy5C2D2o4Rrb3M9fpCGTGlkSLPehGfsCS44TVycb2KfY4R60IZMKWB5Iyk05FISMPx+we2r17Fl52KcYML1Ul4ziNhYwgHjMDdjsyX6lXcNlHR2stNmxGLBI1ROgP7Se2KwC5nrcwxHUCM5UByrkPAT7UrArsdAo5t0RfvIfBIk6WPwM+hSNDIlUPAr/UI2OcQ0NU0AcDtB8TxQxRgSZMEdDky9rWDgJ5IBNwSm4DdAZfAbRR5ssnyFIHfOrEEdv3fN8FtzjF4helM59d6nd8Q4Bi82iHg4yI7QmtDd75RR6jPnXatKi6QK7w+nytMLnYq7gyhPKeBz0S+DH3n7G2XNnIdPimUER28Ds92BnWMZHf9BiKDToOVoQzJERAZjRQQWeWQ+16eBksdAnaYkiPJVJnszx1TNyCP8oKiF5uSIkmmyEaDZuVrKPKKM20GTEmRpOvkOf58kDxz4nN4ZTNcZMoYEs9+GDm9MQEibzrTZ5jkQaYkSO8iw87ov96MkLnex9EHTElA4D7v6GvuXkORNY6gv3VTMQWH5ixlNnHgsVaEdXvHyPckjzcFhV7hM15fZel2h06RGSpi4jLJw/Sm59i5h+RpYYRbe6OXJDVI8nBT3CQp0NrrwioBer3wlM6EE4IqaT4vaKtn24o4yoDVnqIRkudGUZbHHvIcvbV6Nq2OqxTo9VJl/9KvyB1Ilb2nSqrssvYYYO21SYJylvnhdiRTpB7eUJVk6Wti666Wyz8ZQ8xukMEvULrUKPJqjXT5zrwiob720ATqau+CdEZockILLzuSb/sqw0+PL8KDiSpJCJvrPJnZmb4GW6bRZnVN02cyM9Oiv+ck/2mdSt3s5lbEJzM+knc/8R5NjaXhsuI9mqrxbE6/DL3b4rO5vamMHpJHmjKClVdhF6WxuY1JtjnwQ/LpSy9YlaK/f0zz/rXOw0mbIqzvaUzD/KfxL78i3f2lW75FAAAAAElFTkSuQmCC"></img></li>
          <li onClick={() => navigate('/')}>Cerrar Sesión </li>
        </ul>
      </div>
      <div className="right-container">
      <div className="stars">
        {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
         {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
      </div>
      <img src={logoBB} className="imagen-bro" alt="Brocast Bank Logo" />
      </div>
    </div>
  );
}

export default MainPage;
