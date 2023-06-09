import { css } from '@emotion/react';
import styled from '@emotion/styled';
import { PALETTE } from '@/constants/palette';
import { BORDER_RADIUS, BOX_SHADOW } from '@/constants/styles';
import { BadgePropsInterface } from '@/components/atoms/Badge';

export const badgeColorList = {
  up: css`
    color: ${PALETTE.RED400};
    background-color: ${PALETTE.RED100};
  `,
  down: css`
    color: ${PALETTE.BLUE400};
    background-color: ${PALETTE.BLUE100};
  `,
  same: css`
    color: ${PALETTE.BRAND400};
    background-color: ${PALETTE.BRAND100};
  `,
};

export const Badge = styled.div<Partial<BadgePropsInterface>>`
  ${({ type }) => type && badgeColorList[type]}
  width: ${({ width }) => width};
  height: ${({ width }) => width};

  /* 공통 css */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  line-height: 1rem;

  border-radius: ${BORDER_RADIUS.ROUND};
  box-shadow: ${BOX_SHADOW.BLACK_SM};

  cursor: pointer;
  p {
    margin: 0;
  }
`;
